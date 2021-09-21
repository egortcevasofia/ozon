package com.example.ozon.repository;

import com.example.ozon.criterias.GoodPageAndSort;
import com.example.ozon.criterias.GoodSearchCriteria;
import com.example.ozon.domain.Good;
import com.example.ozon.dto.GoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class GoodCritariaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;
    private final GoodMapper goodMapper;

    @Autowired
    public GoodCritariaRepository(EntityManager entityManager, GoodMapper goodMapper) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
        this.goodMapper = goodMapper;
    }

    public Page<Good> findAllWithFilters(GoodPageAndSort goodPageAndSort, GoodSearchCriteria goodSearchCriteria) {
        CriteriaQuery<Good> criteriaQuery = criteriaBuilder.createQuery(Good.class);// какой тип возвращаем
        Root<Good> employeeRoot = criteriaQuery.from(Good.class);// откуда берем данные
        Predicate predicate = getPredicate(goodSearchCriteria, employeeRoot);//в этом методе добавляем все условия и он гненрирует их в одно
        criteriaQuery.where(predicate);
        setOrder(goodPageAndSort, criteriaQuery, employeeRoot);

        TypedQuery<Good> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(goodPageAndSort.getPageNumber() * goodPageAndSort.getPageSize());
        typedQuery.setMaxResults(goodPageAndSort.getPageSize());

        Pageable pageable = getPageable(goodPageAndSort);

        long employeesCount = getEmployeesCount(predicate);


        return new PageImpl<Good>(typedQuery.getResultList(), pageable, employeesCount);
    }

    private Predicate getPredicate(GoodSearchCriteria goodSearchCriteria,
                                   Root<Good> employeeRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(goodSearchCriteria.getName())) {
            predicates.add(
                    criteriaBuilder.like(employeeRoot.get("name"), "%" + goodSearchCriteria.getName() + "%")
            );
        }
        if (goodSearchCriteria.getShopId() != 0) {
            predicates.add(
                    criteriaBuilder.equal(employeeRoot.get("shop"), goodSearchCriteria.getShopId())
            );
        }
        if (goodSearchCriteria.getCategoryId() != 0) {
            predicates.add(
                    criteriaBuilder.equal(employeeRoot.get("category"), goodSearchCriteria.getCategoryId())
            );
        }
        if (Objects.nonNull(goodSearchCriteria.getPriceMin()) || Objects.nonNull(goodSearchCriteria.getPriceMax())) {
            predicates.add(
                    criteriaBuilder.between(employeeRoot.get("price"),
                            goodSearchCriteria.getPriceMin(),
                            goodSearchCriteria.getPriceMax())
            );
        }

        if (goodSearchCriteria.getHasFoto().equals(true)) {
            predicates.add(
                    criteriaBuilder.isNotEmpty(employeeRoot.get("foto"))
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(GoodPageAndSort goodPageAndSort,
                          CriteriaQuery<Good> criteriaQuery,
                          Root<Good> employeeRoot) {
        if (goodPageAndSort.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(employeeRoot.get(goodPageAndSort.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(employeeRoot.get(goodPageAndSort.getSortBy())));
        }
    }

    private Pageable getPageable(GoodPageAndSort goodPageAndSort) {
        Sort sort = Sort.by(goodPageAndSort.getSortDirection(), goodPageAndSort.getSortBy());
        return PageRequest.of(goodPageAndSort.getPageNumber(), goodPageAndSort.getPageSize(), sort);
    }

    private long getEmployeesCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Good> countRoot = countQuery.from(Good.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}

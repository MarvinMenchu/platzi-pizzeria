package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.OrderEntity;
import com.platzi.pizza.persistence.projection.OrderSumary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);
    List<OrderEntity> findAllByMethodIn(List<String> methods);

    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);
    @Query(value = "select  " +
            "po.id_order as idOrder, cu.name as customerName, " +
            "po.date as orderDate,  " +
            "po.total as orderTotal, " +
            "group_concat(pi.name) as pizzaNames " +
            "from " +
            "pizza_order po " +
            "inner join customer cu on po.id_customer = cu.id_customer " +
            "inner join order_item oi on po.id_order = oi.id_order " +
            "inner join pizza pi on oi.id_pizza = pi.id_pizza " +
            "where po.id_order = :orderId " +
            "group by po.id_order, cu.name, po.date, po.total", nativeQuery = true)
    OrderSumary findSummary(@Param("orderId") int orderId);

    @Procedure(value = "take_random_pizza_order", outputParameterName = "order_taken")
    boolean saveRandomOrder(@Param("id_customer") String idCustomer, @Param("method") String method);
}

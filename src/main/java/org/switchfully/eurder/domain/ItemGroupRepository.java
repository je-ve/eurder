package org.switchfully.eurder.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ItemGroupRepository extends JpaRepository<ItemGroup, String> {

    List<ItemGroup> findAllByOrderIdIn(Collection<String> orderIds);
    List<ItemGroup> findAllByOrderId(String orderId);
}

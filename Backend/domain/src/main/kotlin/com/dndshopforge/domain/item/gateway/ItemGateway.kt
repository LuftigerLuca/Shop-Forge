package com.dndshopforge.domain.item.gateway

import com.dndshopforge.domain.item.model.Item
import com.dndshopforge.domain.item.model.ItemId
import com.dndshopforge.domain.shared.gateway.ReadWriteGateway

interface ItemGateway : ReadWriteGateway<Item, ItemId>

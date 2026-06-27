package com.dndshopforge.domain.gateway

import com.dndshopforge.domain.model.item.Item
import com.dndshopforge.domain.model.item.ItemId

interface ItemGateway : ReadWriteGateway<Item, ItemId>

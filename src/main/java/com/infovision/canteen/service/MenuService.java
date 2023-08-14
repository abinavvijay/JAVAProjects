package com.infovision.canteen.service;

import java.util.List;
import java.util.UUID;

import com.infovision.canteen.dto.admin.EditMenuItemDto;
import com.infovision.canteen.dto.admin.MenuItemDto;
import com.infovision.canteen.exception.MenuItemException;
import com.infovision.canteen.model.admin.MenuItem;

public interface MenuService {

	List<MenuItemDto> addMenuItems(List<MenuItemDto> menuItemDtos,UUID adminId) throws MenuItemException;

	EditMenuItemDto editMenuItem(EditMenuItemDto editMenuItemDto, UUID adminId)throws MenuItemException;

	List<MenuItem> viewMenuItems(UUID adminId) throws MenuItemException;

	String deleteMenuItem(UUID adminId, UUID menuItemId) throws MenuItemException;

	

}

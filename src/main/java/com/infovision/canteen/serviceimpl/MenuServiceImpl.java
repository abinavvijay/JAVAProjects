package com.infovision.canteen.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.dto.admin.EditMenuItemDto;
import com.infovision.canteen.dto.admin.MenuItemDto;
import com.infovision.canteen.exception.MenuItemException;
import com.infovision.canteen.model.admin.Admin;
import com.infovision.canteen.model.admin.MenuItem;
import com.infovision.canteen.repository.AdminRepository;
import com.infovision.canteen.repository.MenuItemRepository;
import com.infovision.canteen.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuItemRepository menuItemRepositroy;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public List<MenuItemDto> addMenuItems(List<MenuItemDto> menuItemDtos,UUID adminId) throws MenuItemException {
		// TODO Auto-generated method stub
		Admin admin=adminRepository.getOne(adminId);
		
		if(adminRepository.existsById(adminId))
		{
			
		for(MenuItemDto menuItemDto : menuItemDtos)
		{
			MenuItem menuItem=new MenuItem();
			
			menuItem.setItemName(menuItemDto.getItemName());
			menuItem.setFoodType(menuItemDto.getFoodType());
			menuItem.setImageUrl(menuItemDto.getImageUrl());
			
			menuItem.setAdmin(admin);
			
			menuItemRepositroy.save(menuItem);
		}
		
		}
		else
			throw new MenuItemException("Admin details not found");
		
		return menuItemDtos;
	}

	@Override
	public EditMenuItemDto editMenuItem(EditMenuItemDto editMenuItemDto, UUID adminId) throws MenuItemException {
		// TODO Auto-generated method stub
		
		
		if(adminRepository.existsById(adminId))
		{
			
			if(menuItemRepositroy.existsById(editMenuItemDto.getId()))
			{
				
			MenuItem menuItem=menuItemRepositroy.getOne(editMenuItemDto.getId());
			
			if(menuItem.getAdmin().getId().equals(adminId))
			{
			menuItem.setFoodType(editMenuItemDto.getFoodType());
			menuItem.setImageUrl(editMenuItemDto.getImageUrl());
			menuItem.setItemName(editMenuItemDto.getItemName());
			
			menuItemRepositroy.save(menuItem);
			
			return editMenuItemDto;
			}
			else
				throw new MenuItemException("Menu Item not matched with admin Details");
			
			}
			else
				throw new MenuItemException("MenuItem is not found");
			
		}
		else
			throw new MenuItemException("Admin  not found");
		
		
	}

	@Override
	public List<MenuItem> viewMenuItems(UUID adminId) throws MenuItemException {
		// TODO Auto-generated method stub
		if(adminRepository.existsById(adminId))
		{
		List<MenuItem> menuItems=menuItemRepositroy.getByAdmin(adminId);
		
		if(menuItems.isEmpty())
			throw new MenuItemException("MenuItems List are empty,pls add");
		
		return menuItems;
		}
		else
			throw new MenuItemException("Admin  not found");
	}

	@Override
	public String deleteMenuItem(UUID adminId, UUID menuItemId) throws MenuItemException {
		// TODO Auto-generated method stub
		
		if(adminRepository.existsById(adminId))
		{
			
		List<MenuItem> menuItems=menuItemRepositroy.getByAdmin(adminId);
		
		if(menuItems.isEmpty())
			throw new MenuItemException("MenuItems List are empty,pls add");
		
		for(MenuItem menuItem: menuItems)
		{
			if(menuItem.getItemId().equals(menuItemId))
			{
				menuItemRepositroy.delete(menuItem);
				return "MenuItem is Deleted";
			}
		}
		
		}
		else
			throw new MenuItemException("Admin Not found");
		
		return "MenuItem is not found";
	}

}

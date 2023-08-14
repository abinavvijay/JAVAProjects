package com.infovision.canteen.serviceimpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.infovision.canteen.exception.FeedbackException;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.model.feedback.Feedback;
import com.infovision.canteen.model.feedback.ItemFeedback;
import com.infovision.canteen.model.feedback.Website;
import com.infovision.canteen.repository.EmployeeRepository;
import com.infovision.canteen.repository.FeedbackRepository;
import com.infovision.canteen.repository.ItemFeedbackRepository;
import com.infovision.canteen.repository.RestaurantItemRepository;
import com.infovision.canteen.repository.WebsiteRepository;
import com.infovision.canteen.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private WebsiteRepository websiteRepository;

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private RestaurantItemRepository restaurantItemRepository;

	@Autowired
	private ItemFeedbackRepository itemFeedbackRepository;
	
	@Override
	public String postWebsiteFeedback(UUID empId, String message, double rating) throws FeedbackException {
		// TODO Auto-generated method stub

		if (employeeRepository.existsById(empId)) {

			Feedback feedback = new Feedback();

			feedback.setEmployee(employeeRepository.getOne(empId));
			feedback.setFeedback(message);
			feedback.setRating(rating);
			feedback.setWebsite(websiteRepository.getByName("INFO_EAT"));
			feedbackRepository.save(feedback);
		}

		else
			throw new FeedbackException("Employee not found");

		return "FeedBack Saved Successfully";
	}

	@Override
	public List<Feedback> getWebisteFeedback() throws FeedbackException {
		// TODO Auto-generated method stub

		List<Feedback> feedbackList = feedbackRepository.findAllByName("INFO_EAT");

		if (feedbackList.isEmpty())
			throw new FeedbackException("Feedback are empty");

		return feedbackList;
	}

	@Override
	public String postOrderFeedback(UUID empId, String message, double rating, UUID itemId) throws FeedbackException {
		// TODO Auto-generated method stub

		if (employeeRepository.existsById(empId)) {

			if (restaurantItemRepository.existsById(itemId)) {
				ItemFeedback feedback = new ItemFeedback();

				feedback.setEmployee(employeeRepository.getOne(empId));
				feedback.setFeedback(message);
				feedback.setRating(rating);
				feedback.setRestaurantItem(restaurantItemRepository.getOne(itemId));
				itemFeedbackRepository.save(feedback);
			} else
				throw new FeedbackException("Item not Found");
		}

		else
			throw new FeedbackException("Employee not found");

		return "FeedBack Saved Successfully";

	}

	@Override
	public List<ItemFeedback> getOrderFeedback(UUID itemId) throws FeedbackException {
		// TODO Auto-generated method stub
		if (restaurantItemRepository.existsById(itemId)) {
		List<ItemFeedback> feedbackList = itemFeedbackRepository.findByItemId(itemId);

		if (feedbackList.isEmpty())
			throw new FeedbackException("NO feedBack given for this Item");

		return feedbackList;
		} else
			throw new FeedbackException("Item not Found");
	}

}

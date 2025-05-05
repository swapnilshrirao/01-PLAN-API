package in.swapnilIt.service;

import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.swapnilIt.entity.Plan;
import in.swapnilIt.entity.PlanCategory;
import in.swapnilIt.repo.PlanCategoryRepo;
import in.swapnilIt.repo.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private PlanCategoryRepo planCategoryRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {

		List<PlanCategory> categories = planCategoryRepo.findAll();
		Map<Integer, String> categoryMap = new HashMap<>();

		categories.forEach(category -> {
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});

		return categoryMap;
	}

	@Override
	public boolean savePlan(Plan plan) {

		Plan saved = planRepo.save(plan);

		/*
		 * if(saved.getPlanId() !=null) { return true; }else{ return false; } freshers
		 * code
		 */

		// return saved.getPlanId()!=null ? true : false; 1 years exp code

		return saved.getPlanId() != null; // matre code
	}

	@Override
	public List<Plan> getAllPlans() {
		return planRepo.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {

		Optional<Plan> findById = planRepo.findById(planId); // ctrl+1+enter to get return type..its shorcut

		if (findById.isPresent()) {
			return findById.get();

		}
		return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {

		planRepo.save(plan);
		return plan.getPlanId() != null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status = false;
		try {
			planRepo.deleteById(planId);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {

		Optional<Plan> findById = planRepo.findById(planId);

		if (findById.isPresent()) {
			Plan plan = findById.get();
			plan.setActiveSw(status);
			planRepo.save(plan);
			return true;

		}
		return false;
	}

}

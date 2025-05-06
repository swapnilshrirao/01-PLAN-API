package in.swapnilIt.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import in.swapnilIt.entity.Plan;
import in.swapnilIt.props.AppProperties;
import in.swapnilIt.service.PlanService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class PlanRestController {

	/*
	 * Fieled Injection
	 * 
	 * @Autowired private PlanService planService;
	 */

	/*
	 * @Autowired private AppProperties appProps;
	 */

	private PlanService planService;

	//we use this global variables in all the methods
	private Map<String, String> messages;

	public PlanRestController(PlanService planService, AppProperties appProps) {

		this.planService = planService;
		this.messages = appProps.getMessages();
	}

//every time we created the map at  method level..to remove it we will create map at class level
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {

		Map<Integer, String> categories = planService.getPlanCategories();

		return new ResponseEntity<>(categories, HttpStatus.OK);

	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(Plan plan) {

		String responseMsg = "";
		boolean isSaved = planService.savePlan(plan);
		
		/*
		 * duplicate code Map<String, String> messages = appProps.getMessages();
		 */
		if (isSaved) {

			// responseMsg = "Plan Saved";
			responseMsg = messages.get("planSaveSucc");

		} else {
			// responseMsg = "Plan Not Saved";
			responseMsg = messages.get("planSaveFail");
		}

		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);

	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plans() {

		List<Plan> allPlans = planService.getAllPlans();

		return new ResponseEntity<>(allPlans, HttpStatus.OK);

	}

	@GetMapping("plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {

		Plan plan = planService.getPlanById(planId);

		return new ResponseEntity<>(plan, HttpStatus.OK);
	}

	@DeleteMapping("plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {

		boolean isDeleted = planService.deletePlanById(planId);
		/*
		 * duplicate code Map<String, String> messages = appProps.getMessages();
		 */
		String msg = "";

		if (isDeleted) {

			// msg = "PLAN DELETED";

			msg = messages.get("planDeleteSucc");

		} else {
			// msg = "PLAN NOT DELETED";
			msg = messages.get("planDeleteFail");

		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@PutMapping("plan/{planId}")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {

		boolean isUpdated = planService.updatePlan(plan);
		/*
		 * duplicate code Map<String, String> messages = appProps.getMessages();
		 */

		String msg = "";

		if (isUpdated) {

//msg = "PLAN UPDATED";

			msg = messages.get("planUpdateSucc");

		} else {
			// msg = "PLAN NOT UPDATED";
			msg = messages.get("planUpdateFail");

		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status) {

		String msg = "";

		boolean isStatusChanged = planService.planStatusChange(planId, status);

		/*
		 * duplicate code Map<String, String> messages = appProps.getMessages();
		 */

		if (isStatusChanged) {
			// msg = "Status Changed";
			msg = messages.get("planStatusChange");

		} else {

			// msg = "Status Not Changed";
			msg = messages.get("planStatusChangeFail");
		}

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}

package in.swapnilIt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.swapnilIt.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer>{
	
	

}

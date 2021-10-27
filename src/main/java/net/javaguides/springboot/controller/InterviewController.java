package net.javaguides.springboot.controller;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Interview;
import net.javaguides.springboot.repository.InterviewRepository;
import net.javaguides.springboot.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/s1/")
public class InterviewController {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private InterviewRepository interviewRepository;



	@GetMapping("/interviews")
	public List<Interview> getAllLInterviews(){
		return interviewRepository.findAll();
	}


	@PostMapping("/interviews/job/{id}")
	public Interview createInterview(@PathVariable Long id, @RequestBody Interview interview) {

		return jobRepository.findById(id).map(job -> {
			interview.setJob(job);
			return interviewRepository.save(interview);
		}).orElseThrow(() -> new ResourceNotFoundException("JobId " + id + " not found"));

	}
	

	@GetMapping("/interviews/{id}")
	public ResponseEntity<Interview> getInterviewById(@PathVariable Long id) {
		Interview interview = interviewRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Interview not exist with id :" + id));
		return ResponseEntity.ok(interview);
	}
	

	
	@PutMapping("/interviews/{id}")
	public ResponseEntity<Interview> updateInterview(@PathVariable Long id, @RequestBody Interview interviewDetails){
		Interview interview = interviewRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Interview not exist with id :" + id));

		interview.setDate(interviewDetails.getDate());
		interview.setTime(interviewDetails.getTime());
		interview.setJob(interviewDetails.getJob());


		Interview updatedinInterview = interviewRepository.save(interview);
		return ResponseEntity.ok(updatedinInterview);
	}
	

	@DeleteMapping("/interviews/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteInterviews(@PathVariable Long id){
		Interview interview = interviewRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Interview not exist with id :" + id));

		interviewRepository.delete(interview);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}

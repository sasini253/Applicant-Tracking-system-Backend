package net.javaguides.springboot.controller;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Job;
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
public class JobController {

	@Autowired
	private JobRepository jobRepository;

	@GetMapping("/jobs")
	public List<Job> getAllJobs(){
		return jobRepository.findAll();
	}

	@PostMapping("/jobs")
	public Job createJob(@RequestBody Job job) {
		return jobRepository.save(job);
	}
	

	@GetMapping("/jobs/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id) {
		Job job = jobRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Job not exist with id :" + id));
		return ResponseEntity.ok(job);
	}
	

	
	@PutMapping("/jobs/{id}")
	public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job jobDetails){
		Job job = jobRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Job not exist with id :" + id));

		job.setJobName(jobDetails.getJobName());
		job.setJobType(jobDetails.getJobType());
		job.setSkills(jobDetails.getSkills());
		job.setSalary(jobDetails.getSalary());

		Job updatedJob = jobRepository.save(job);
		return ResponseEntity.ok(updatedJob);
	}

	@DeleteMapping("/jobs/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteJob(@PathVariable Long id){
		Job job = jobRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Job not exist with id :" + id));

		jobRepository.delete(job);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}

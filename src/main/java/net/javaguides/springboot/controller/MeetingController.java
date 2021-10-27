package net.javaguides.springboot.controller;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Meeting;

import net.javaguides.springboot.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/s1/")
public class MeetingController {

	@Autowired
	private MeetingRepository meetingRepository;


	@GetMapping("/meetings")
	public List<Meeting> getAllMeetings(){
		return meetingRepository.findAll();
	}


	@PostMapping("/meetings")
	public Meeting createMeeting(@RequestBody Meeting meeting) {
		return meetingRepository.save(meeting);
	}


	@GetMapping("/meetings/{id}")
	public ResponseEntity<Meeting> getMeetingById(@PathVariable Long id) {
		Meeting meeting = meetingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Meeting not exist with id :" + id));
		return ResponseEntity.ok(meeting);
	}

	@PutMapping("/meetings/{id}")
	public ResponseEntity<Meeting> updateMeeting(@PathVariable Long id, @RequestBody Meeting meetingDetails){
		Meeting meeting = meetingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Meeting not exist with id :" + id));

		meeting.setDepartment(meetingDetails.getDepartment());
		meeting.setDate(meetingDetails.getDate());
		meeting.setTime(meetingDetails.getTime());


		Meeting updatedMeeting = meetingRepository.save(meeting);
		return ResponseEntity.ok(updatedMeeting);
	}


	@DeleteMapping("/meetings/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteMeeting(@PathVariable Long id){
		Meeting meeting = meetingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Meeting not exist with id :" + id));

		meetingRepository.delete(meeting);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}


}

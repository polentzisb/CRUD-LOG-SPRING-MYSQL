package com.polentzi.tvshow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.polentzi.tvshow.models.Show;
import com.polentzi.tvshow.repositories.ShowRepository;

@Service
public class ShowService {
	private final ShowRepository showRepository;
	public ShowService(ShowRepository showRepository) {
		this.showRepository=showRepository;
	}
	 public List<Show> allShows() {
	        return showRepository.findAll();
	    }

	    public Show createShow(Show show) {
	        return showRepository.save(show);
	    }

	    public Show findShow(Long id) {
	        Optional<Show> optionalShow = showRepository.findById(id);
	        return optionalShow.orElse(null);
	    }

	    public List<Show> searchShow(String title) {
	        return showRepository.findByNetworkContaining(title);
	    }

	    public void deleteShow(Long id) {
	        showRepository.deleteById(id);
	    }

	    public Show updateShow(Show updatedShow) {
	        return showRepository.save(updatedShow);
	    }
	
	
}
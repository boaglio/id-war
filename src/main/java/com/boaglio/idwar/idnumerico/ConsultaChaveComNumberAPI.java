package com.boaglio.idwar.idnumerico;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.LongStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boaglio.idwar.CacheID;
import com.boaglio.idwar.StressTestResponse;

@RequestMapping("/api/v1")
@RestController
public class ConsultaChaveComNumberAPI {
	
	@Autowired
	private ChaveComNumberRepository chaveComNumberRepository;
	
	static Logger logger = LoggerFactory.getLogger(ConsultaChaveComNumberAPI.class);
	
	@GetMapping("/chave-com-number/{id}")
	public ResponseEntity<ConsultaChaveComNumberResponse> consultaChaveComNumber(@PathVariable Long id) {
		
		Instant start = Instant.now();
		
		Optional<ChaveComNumber> chaveComNumberOpt = chaveComNumberRepository.findById(id);
		if (chaveComNumberOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} 
		var row = chaveComNumberOpt.get();
		
		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ConsultaChaveComNumberResponse(row.getId(),row.getCliente(),row.getTempo(),timeElapsed));
	}

	@GetMapping("/stress-test-chave-com-number/{hits}")
	public ResponseEntity<StressTestResponse> stressTestChaveComNumber(@PathVariable Long hits) {
		
		Instant start = Instant.now();
		
		LongStream.range(0, hits).parallel().forEach(contador -> {
    		 
			consultaChaveComNumber(CacheID.getRandomIdNumerico());
			
    		if ( contador%1000 ==0 )
    			logger.info(">> Acessos em CHAVE COM NUMBER: {}",contador);
    	  }
    	);
		
		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(new StressTestResponse(hits,LocalDateTime.now(),timeElapsed));
	}
	
}

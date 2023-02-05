package com.boaglio.idwar;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.LongStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.boaglio.idwar.idnumerico.ChaveComNumber;
import com.boaglio.idwar.idnumerico.ChaveComNumberRepository;
import com.boaglio.idwar.ulid.ChaveComULID;
import com.boaglio.idwar.ulid.ChaveComULIDRepository;
import com.boaglio.idwar.uuid.ChaveComUUID;
import com.boaglio.idwar.uuid.ChaveComUUIDRepository;

import de.huxhorn.sulky.ulid.ULID;

@Component
public class CargaDeDados implements ApplicationRunner {

	static Logger logger = LoggerFactory.getLogger(CargaDeDados.class);

	@Autowired
	private ChaveComNumberRepository chaveComNumberRepository;

	@Autowired
	private ChaveComUUIDRepository chaveComUUIDRepository;

	@Autowired
	private ChaveComULIDRepository chaveComULIDRepository;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {

		if (VariaveisGlobais.FAZ_CARGA) {
			Instant start = Instant.now();
			logger.info(">>> Iniciando carga de dados... ");

			logger.info(">>> Iniciando CHAVE COM NUMBER ... ");
			LongStream.range(0, VariaveisGlobais.QUANTIDADE_DE_REGISTROS).parallel().forEach(contador -> {
				chaveComNumberRepository
						.saveAndFlush(new ChaveComNumber(contador, "Dev Multitask", LocalDateTime.now()));
				CacheID.idnumerico.add(contador);
				if (contador % 1000 == 0) {
					logger.info(">> Registros gravados NUMBER: {} - Thread {}", contador,Thread.currentThread().getId());
				}
			});

			logger.info(">>> Iniciando CHAVE COM UUID... ");
			LongStream.range(0, VariaveisGlobais.QUANTIDADE_DE_REGISTROS).parallel().forEach(contador -> {
				String id = UUID.randomUUID().toString();
				chaveComUUIDRepository.saveAndFlush(new ChaveComUUID(id, "Dev Multitask", LocalDateTime.now()));
				CacheID.uuid.add(id);
				if (contador % 1000 == 0) {
					logger.info(">> Registros gravados UUID: {} - Thread {}", contador,Thread.currentThread().getId());
				}
			});

			logger.info(">>> Iniciando CHAVE COM ULID... ");
			ULID ulid = new ULID();
			LongStream.range(0, VariaveisGlobais.QUANTIDADE_DE_REGISTROS).parallel().forEach(contador -> {
				String id = ulid.nextULID();
				chaveComULIDRepository.saveAndFlush(new ChaveComULID(id, "Dev Multitask", LocalDateTime.now()));
				CacheID.ulid.add(id);
				if (contador % 1000 == 0) {
					logger.info(">> Registros gravados ULID: {} - Thread {}", contador,Thread.currentThread().getId());
				}
			});

			Instant finish = Instant.now();
			long timeElapsed = Duration.between(start, finish).toMillis();

			logger.info(">>> Carga de dados finalizada: {}ms", timeElapsed);
		} else {
			logger.info(">>> Carregando cache CHAVE COM NUMBER ... ");
			LongStream.range(0, VariaveisGlobais.QUANTIDADE_DE_REGISTROS).parallel().forEach(contador -> {
				CacheID.idnumerico.add(contador);
			});

			logger.info(">>> Carregando cache UUID ... ");
			LongStream.range(0, VariaveisGlobais.QUANTIDADE_DE_REGISTROS).parallel().forEach(contador -> {
				List<ChaveComUUID> rows = chaveComUUIDRepository.findAll(); 
				rows.parallelStream().forEach(row -> CacheID.uuid.add(row.getId()));
			});

			logger.info(">>> Carregando cache ULID ... ");
			LongStream.range(0, VariaveisGlobais.QUANTIDADE_DE_REGISTROS).parallel().forEach(contador -> {
				List<ChaveComULID> rows = chaveComULIDRepository.findAll(); 
				rows.parallelStream().forEach(row -> CacheID.ulid.add(row.getId()));
			});

		}

		logger.info(">> Total de registros nas tabelas:");
		logger.info(">> CHAVE COM NUMBER: {}", chaveComNumberRepository.count());
		logger.info(">> CHAVE COM UUID: {}", chaveComUUIDRepository.count());
		logger.info(">> CHAVE COM ULID: {}", chaveComULIDRepository.count());

		logger.info(">> Total de registros no cache:");
		logger.info(">> CHAVE COM NUMBER: {}", CacheID.idnumericoCount());
		logger.info(">> CHAVE COM UUID: {}", CacheID.uuidCount());
		logger.info(">> CHAVE COM ULID: {}", CacheID.ulidCount());

	}

}
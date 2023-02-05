package com.boaglio.idwar;

import java.time.LocalDateTime;

public record StressTestResponse(Long totalRows,LocalDateTime tempo,Long tempoProcessamento) {

}

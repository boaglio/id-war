package com.boaglio.idwar.uuid;

import java.time.LocalDateTime;

public record ConsultaChaveComUUIDResponse(String id, String cliente,LocalDateTime tempo,Long tempoProcessamento) {

}

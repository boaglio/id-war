package com.boaglio.idwar.ulid;

import java.time.LocalDateTime;

public record ConsultaChaveComULIDResponse(String id, String cliente,LocalDateTime tempo,Long tempoProcessamento) {

}

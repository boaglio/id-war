package com.boaglio.idwar;

import java.util.List;
import java.util.SplittableRandom;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Cache para testar a carga
 */
public class CacheID {

	public static List<Long> idnumerico = new CopyOnWriteArrayList<>();
	public static List<String> uuid = new CopyOnWriteArrayList<>();
	public static List<String> ulid = new CopyOnWriteArrayList<>();

	static SplittableRandom splittableRandom = new SplittableRandom();

	static int getRandomId() {
		return splittableRandom.nextInt(0, VariaveisGlobais.QUANTIDADE_DE_REGISTROS);
	}

	public static Long getRandomIdNumerico() {
		return idnumerico.get(getRandomId());
	}

	public static String getRandomUUID() {
		return uuid.get(getRandomId());
	}

	public static String getRandomULID() {
		return ulid.get(getRandomId());
	}

	public static long idnumericoCount() {
		return idnumerico.size();
	}

	public static long uuidCount() {
		return uuid.size();
	}

	public static long ulidCount() {
		return ulid.size();
	}
}

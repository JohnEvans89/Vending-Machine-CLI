package com.techelevator;

import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SalesLog implements Closeable, Loggers {

	private File salesFile;
	private PrintWriter writer;
	DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
	LocalDateTime time = LocalDateTime.now();
	

	public SalesLog(String salesFile) throws Exception {

		this.salesFile = new File(salesFile);

		if (this.salesFile.exists()) {
			try {
				writer = new PrintWriter(new FileWriter(this.salesFile, true));
			} catch (Exception ex) {
				throw new IOException(ex.toString());
			}
		} else {
			writer = new PrintWriter(this.salesFile);
		}

	}
	@Override
	public void write(String logMessage) {

		try {
			writer.println(logMessage);
			writer.flush();
		} catch (Exception ex) {
			throw ex;
		}

	}

	@Override
	public void close() throws IOException {

		try {
			writer.close();
		} catch (Exception ex) {
			throw new IOException(ex.getMessage());
		} finally {
		}

	}

}

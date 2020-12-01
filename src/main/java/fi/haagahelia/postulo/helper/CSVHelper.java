package fi.haagahelia.postulo.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import fi.haagahelia.postulo.domain.Requirement;

public class CSVHelper {

	
	public static ByteArrayInputStream requirementsToCSV(List<Requirement> requirements) {
	    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

	    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
	        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
	      for (Requirement requirement : requirements) {
	        List<String> data = Arrays.asList(
	              String.valueOf(requirement.getId()),
	              requirement.getReqid(),
	              String.valueOf(requirement.getType()),
	              requirement.getSummary(),
	              requirement.getRationale(),
	              requirement.getPriority(),
	              requirement.getSource(),
	              requirement.getOwner(),
	              String.valueOf(requirement.getRdate())
	            );

	        csvPrinter.printRecord(data);
	      }

	      csvPrinter.flush();
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
	    }
	  }
	


}

package co.edu.unbosque.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Bryan Baron
 */
@Data
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BirthDate {
	private String bDay;
	private String bMonth;
	private String bYear;
}

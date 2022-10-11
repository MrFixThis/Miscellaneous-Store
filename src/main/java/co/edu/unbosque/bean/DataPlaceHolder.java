package co.edu.unbosque.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bryan Baron
 */
@Data
@NoArgsConstructor @AllArgsConstructor
public class DataPlaceHolder<T> {
	private T data;
}

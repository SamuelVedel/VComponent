package fr.svedel.vcomponent;

/**
 * This class implements adjustable integers that are used in the whole package.
 * <p>
 * These adjustable integers are very importante to easily make every component
 * totaly the same (but in a different scale) when the window is reshaped
 * 
 * @author Samuel Vedel
 *
 */
public class VAdjustInt {
	/** représente la valeur initiale de la variable */
	private int value;
	/** valeur de la variable une fois ajustée */
	private int currentValue;
	
	/**
	 * Constructor of {@code VAdjustInt}
	 *
	 * @param value the value when the scale is 1 (the initial value)
	 */
	public VAdjustInt(int value) {
		this.value = value;
	}
	
	/**
	 * Return the initial value of the variable.
	 * This is the value when the scale is 1.
	 * 
	 * @return the initial value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Set the initial value of the variable.
	 * This is the value when the scale is 1.
	 *
	 * @param value the new initial value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * Return the adjusted value of the variable
	 * 
	 * @return the adjusted value
	 */
	public int getCurrentValue() {
		return currentValue;
	}
	
	/**
	 * Set manualy the adjusted value
	 *
	 * @param value the new adjusted value
	 */
	public void setCurrentValue(int value) {
		currentValue = value;
	}
	
	/**
	 * Automacialy adjust the value, as if the initial value were {@code value}.
	 * The parameter {@code reference} is another {@code VAdjustInt}
	 * for wich the initial value is the value for wich the scale
	 * is 1, and the current value of the reference is used to
	 * know the scale.
	 * <p>
	 * So the adjusted value after this function is :
	 * {@code reference.getCurrentValue()*value/reference.getValue()}
	 * <p>
	 * Or set the adjusted value as {@code value} if the reference is {@code null}
	 *
	 * @param value a place holder of the initial value
	 * @param reference the reference {@code VAdjustInt} that is used to
	 * scale the value
	 */
	public void adjust(int value, VAdjustInt reference) {
		if (reference == null) setCurrentValue(value);
		else {
			setCurrentValue(reference.getCurrentValue()*value/reference.getValue());
		}
	}
	
	/**
	 * Automacialy adjust the value.
	 * The parameter {@code reference} is another {@code VAdjustInt}
	 * for wich the initial value is the value for wich the scale
	 * is 1, and the current value of the reference is used to
	 * know the scale.
	 * <p>
	 * So the adjusted value after this function is :
	 * {@code reference.getCurrentValue()*this.getValue()/reference.getValue()}
	 * <p>
	 * Or set the adjusted value as the initial value if the reference is {@code null}
	 *
	 * @param reference the reference {@code VAdjustInt} that is used to
	 * scale the value
	 */
	public void adjust(VAdjustInt reference) {
		adjust(getValue(), reference);
	}
	
	/**
	 * Automacialy set the initial value to match the adjusted value,
	 * as if the adjusted value were {@code currentValue}.
	 * The parameter {@code reference} is another {@code VAdjustInt}
	 * for wich the initial value is the value for wich the scale
	 * is 1, and the current value of the reference is used to
	 * know the scale.
	 * <p>
	 * So the initial value after this function is :
	 * {@code reference.getValue()*currentValue/reference.getCurrentValue()}
	 *
	 * @param currentValue a place holder of adjusted value
	 * @param reference the reference {@code VAdjustInt} that is used to
	 * scale the value
	 */
	public void reverseAdjust(int currentValue, VAdjustInt reference) {
		setValue(reference.getValue()*currentValue/reference.getCurrentValue());
	}
	
	/**
	 * Automacialy set the initial value to match the adjusted value.
	 * The parameter {@code reference} is another {@code VAdjustInt}
	 * for wich the initial value is the value for wich the scale
	 * is 1, and the current value of the reference is used to
	 * know the scale.
	 * <p>
	 * So the initial value after this function is :
	 * {@code reference.getValue()*this.getCurrentValue()/reference.getCurrentValue()}
	 *
	 * @param reference the reference {@code VAdjustInt} that is used to
	 * scale the value
	 */
	public void reverseAdjust(VAdjustInt reference) {
		reverseAdjust(getCurrentValue(), reference);
	}
}

package AngrtyBirdsApp;

public class ElementFactory implements IElementFactory{
	
	public Element createBird() {
		return new Bird();
	}
	public Element createPig() {
		return new Pig();
	}
	public Element createDecor() {
		return new Decor();
	}
	public Element createGravity() {
		return new Gravity();
	}
	public Element createTerran() {
		return new Terran();
	}

}

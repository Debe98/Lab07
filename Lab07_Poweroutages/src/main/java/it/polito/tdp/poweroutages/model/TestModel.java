package it.polito.tdp.poweroutages.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println(model.getNercList());
		System.out.println(model.getPowerOutagesByNerc(model.getNercList().get(1)));
		System.out.println(model.getWorstPowerOutagesByNerc(model.getNercList().get(1), 1, 288));
	}

}

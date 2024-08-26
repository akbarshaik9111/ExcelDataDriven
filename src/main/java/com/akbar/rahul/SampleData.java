package com.akbar.rahul;

import java.io.IOException;
import java.util.List;

public class SampleData {

	public static void main(String[] args) throws IOException {
		DataDriven dataDriven = new DataDriven();
		List<String> list = dataDriven.getData("datadriven", "Delete Profile");
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
		System.out.println(list.get(3));
	}
}

package fr.istic.m1.fstorm;

import java.util.List;

import fr.istic.m1.fstorm.beans.StormComponent;
import fr.istic.m1.fstorm.modules.GenerateJavaBolt;

public class GeneratorJavaTest {
	List<StormComponent> list;
	public GeneratorJavaTest(List<StormComponent> l){
		list=l;
	}
	public void compute() {
		
		GenerateJavaBolt gen = new GenerateJavaBolt("nomdupackage", "buildir");
		for(StormComponent component : list){
			gen.Execute(component);
		}

		}

	}



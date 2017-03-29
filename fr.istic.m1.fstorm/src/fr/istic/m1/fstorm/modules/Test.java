package fr.istic.m1.fstorm.modules;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import fr.istic.m1.fstorm.beans.StormComponent;

public class Test {
	List<StormComponent> list;
	public Test(List<StormComponent> l){
		list=l;
	}
	public void compute() {
		
		GenerateJavaBolt gen = new GenerateJavaBolt("nomdupackage", "buildir");
		for(StormComponent component : list){
			gen.Execute(component);
		}

		}

	}



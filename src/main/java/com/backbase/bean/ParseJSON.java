package com.backbase.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.regexp.internal.recompile;

public class ParseJSON {

	public static void main(String[] args) {
		try {

			File inputStream = new File("c:\\Input.json");

			ObjectMapper objectMapper = new ObjectMapper();

			RootObject root = objectMapper.readValue(inputStream, RootObject.class);

			List<ATM> atmList = root.getATM();
			List<ATM> searchMatchList = new ArrayList<>();

			for (Iterator iterator = atmList.iterator(); iterator.hasNext();) {
				ATM atm = (ATM) iterator.next();

				if (atm.getAddress().getCity().equals("AMSTERDAM")) {

					searchMatchList.add(atm);
				}

			}

			if (searchMatchList != null && searchMatchList.size() > 0) {

				for (Iterator iterator = searchMatchList.iterator(); iterator.hasNext();) {
					ATM atm = (ATM) iterator.next();

					// Pretty print
					String prettyStaff1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(atm);
					System.out.println(prettyStaff1);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static String getParsedJSON(String inputJson, String city) {

		StringBuilder prettyjson = null;

		try {

			ObjectMapper objectMapper = new ObjectMapper();

			RootObject root = objectMapper.readValue(inputJson, RootObject.class);

			List<ATM> atmList = root.getATM();
			List<ATM> searchMatchList = new ArrayList<>();

			for (Iterator iterator = atmList.iterator(); iterator.hasNext();) {
				ATM atm = (ATM) iterator.next();

				if (atm.getAddress().getCity().equals(city.toUpperCase())) {

					searchMatchList.add(atm);
				}

			}

			if (searchMatchList != null && searchMatchList.size() > 0) {

				for (Iterator iterator = searchMatchList.iterator(); iterator.hasNext();) {
					ATM atm = (ATM) iterator.next();

					// Pretty print
					prettyjson.append(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(atm));

				}

			}

			System.out.println(prettyjson.toString());

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
		return prettyjson.toString();

	}

}

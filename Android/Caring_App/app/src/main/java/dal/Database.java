package dal;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;


import bll.Car;
import bll.Fahrzeug;
import service.CarUpd;
import service.CarsList;

public class Database {
    private static Database db = null;
    private static String ipHost = "172.16.119.1:8080";

    private Database() {
    }

    public static Database newInstance() {
        if (db == null) {
            db = new Database();
        }
        return db;
    }
    public static Database newInstance(String ip) {
        if (db == null) {
            db = new Database();
        }
        ipHost = ip;
        return db;
    }

    public ArrayList<Fahrzeug> getAllCars() throws Exception {

        Gson gson = new Gson();
        ArrayList<Fahrzeug> retCars = new ArrayList<Fahrzeug>();

        //each call needs an new instance of async !!
        CarsList controller = new CarsList();
        CarsList.setIpHost(ipHost);

        controller.execute();
        String strFromWebService = controller.get();
        try {
            Type colltype = new TypeToken<ArrayList<Fahrzeug>>(){}.getType();
            retCars = gson.fromJson(strFromWebService,colltype);
        } catch (Exception ex) {
            throw new Exception(strFromWebService);
        }

        System.out.println("**** getAllCars() almost finished");
        System.out.println("**** retCars.size" + retCars.size());

        return retCars;
    }

    public String insertCar(Fahrzeug fz) throws Exception {
        Gson gson = new Gson();

        //each call needs an new instance of async !!
        CarUpd controller = new CarUpd();
        CarUpd.setIPHost(ipHost);

        CarUpd.COMMAND paras[] = new CarUpd.COMMAND[1];
        paras[0] = CarUpd.COMMAND.POST;
        controller.setCar(fz);
        controller.execute(paras);
        return controller.get();
    }


}

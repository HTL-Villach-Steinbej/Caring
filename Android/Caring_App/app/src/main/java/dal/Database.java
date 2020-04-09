package dal;
import com.google.common.reflect.TypeToken;

import java.util.ArrayList;
import java.lang.reflect.Type;
import com.google.gson.Gson;

import bll.Car;
import bll.Fahrzeug;
import bll.Rent;
import bll.SchadenUser;
import bll.User;
import service.CarUpd;
import service.CarsList;
import service.RentPostPut;
import service.ServicePostSchaden;
import service.UserPostPutDelete;

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

    public String updateCar(Fahrzeug fz) throws Exception {
        Gson gson = new Gson();

        //each call needs an new instance of async !!
        CarUpd controller = new CarUpd();
        CarUpd.setIPHost(ipHost);

        CarUpd.COMMAND paras[] = new CarUpd.COMMAND[1];
        paras[0] = CarUpd.COMMAND.PUT;
        controller.setCar(fz);
        controller.execute(paras);
        return controller.get();
    }

    public String insertUser(User user) throws Exception {
        Gson gson = new Gson();

        //each call needs an new instance of async !!
        UserPostPutDelete controller = new UserPostPutDelete();
        UserPostPutDelete.setIPHost(ipHost);

        CarUpd.COMMAND paras[] = new CarUpd.COMMAND[1];
        paras[0] = CarUpd.COMMAND.POST;
        controller.setUser(user);
        controller.execute(paras);
        return controller.get();
    }

    public Rent insertRent(Rent rent) throws Exception {
        Gson gson = new Gson();

        //each call needs an new instance of async !!
        RentPostPut controller = new RentPostPut();
        RentPostPut.setIPHost(ipHost);

        RentPostPut.COMMAND paras[] = new RentPostPut.COMMAND[1];
        paras[0] = RentPostPut.COMMAND.POST;
        controller.setRent(rent);
        controller.execute(paras);
        String strFromWebService = controller.get();
        Rent result = null;
        try {
            Type colltype = new TypeToken<Rent>(){}.getType();
            result = gson.fromJson(strFromWebService,colltype);
        } catch (Exception ex) {
            throw new Exception(strFromWebService);
        }
        return result;
    }

    public String insertSchaden(SchadenUser schadenUser) throws Exception {
        Gson gson = new Gson();

        //each call needs an new instance of async !!
        ServicePostSchaden controller = new ServicePostSchaden();
        ServicePostSchaden.setIPHost(ipHost);

        ServicePostSchaden.COMMAND paras[] = new ServicePostSchaden.COMMAND[1];
        paras[0] = ServicePostSchaden.COMMAND.POST;
        controller.setSchaden(schadenUser);
        controller.execute(paras);
        return controller.get();
    }

}

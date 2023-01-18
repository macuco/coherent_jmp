package com.coherent.reservations.configuration;

import com.coherent.reservations.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;

@Component
public class DataStore {

    @Value("${path.file.reservations}")
    private String pathFile;
    ReservationRepository reservationRepository;
    @PostConstruct
    protected void init() {
        try {
            reservationRepository = this.serializeDataIn();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    protected void destroy() {
        try {
            this.serializeDataOut(reservationRepository);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public ReservationRepository loadRepository() throws IOException, ClassNotFoundException {
        return reservationRepository;
    }

    public void serializeDataOut(ReservationRepository reservationRepository) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(reservationRepository);
        oos.close();
    }

    public ReservationRepository serializeDataIn() throws IOException, ClassNotFoundException {
        if(new File(pathFile).exists()) {
            FileInputStream fin = new FileInputStream(pathFile);
            ObjectInputStream ois = new ObjectInputStream(fin);
            ReservationRepository reservationRepository = (ReservationRepository) ois.readObject();
            ois.close();
            return reservationRepository;
        } else {
            return new ReservationRepository();
        }
    }

}

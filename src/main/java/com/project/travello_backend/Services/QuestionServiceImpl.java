package com.project.travello_backend.Services;

import com.project.travello_backend.Dao.HotelDao;
import com.project.travello_backend.Dao.KeywordDao;
import com.project.travello_backend.Entity.Hotel;
import com.project.travello_backend.Entity.Location;
import com.project.travello_backend.Entity.QuestionKeywords;
import com.project.travello_backend.RequestEntity.QuestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private HotelService hotelservice;

    @Autowired
    private HotelDao hoteldao;

    @Autowired
    private LocationService locationservice;
    @Autowired
    private KeywordDao keyworddao;

    @Override
    public void addKeyword(String keyword) {
//
        Optional<QuestionKeywords> newKeyword = keyworddao.findById(1);
        newKeyword.get().getKeywordss().add(keyword.toLowerCase());
        keyworddao.save(newKeyword.get());
    }

    @Override
    public void addDestinations(String destination){
        Optional<QuestionKeywords> newKeyword = keyworddao.findById(2);
        newKeyword.get().getKeywordss().add(destination.toLowerCase());
        keyworddao.save(newKeyword.get());

    }

    @Override
    public String searchQuestion(String question) {
        Optional<QuestionKeywords> newKeyword = keyworddao.findById(2);

        List<String> keywordss = newKeyword.get().getKeywordss();
        System.out.println(keywordss);
        String destination = null;
        for(String word : keywordss){
            Pattern pattern  = Pattern.compile(word);
            Matcher matcher = pattern.matcher(question);
            if(matcher.find()){
                System.out.println(word);
                destination = word;
            }
        }
        if(question.contains("all destinations")){
            List<Location> locations = locationservice.getAllLocations();
            QuestionResponse answer = new QuestionResponse();
            answer.setMessage("");
            for(Location location: locations){
                answer.concatenateMessage(location.getLocation_name()+"\n");
            }
            return answer.getMessage();
        }
        if(destination == null){
            QuestionResponse answer = new QuestionResponse();
            answer.setMessage("please specify destinaton along with question");
           return answer.getMessage() ;
        }
        if(destination != null && question.contains("cheapest")){
            Hotel hotel = hoteldao.findHotelWithLowestPriceByLocation(destination);
            QuestionResponse answer = new QuestionResponse();
            answer.concatenateMessage(hotel.getHotelName()+"\n");
            answer.concatenateMessage("Price : "+hotel.getHotelPrice()+"\n");
            answer.concatenateMessage(hotel.getHotelAddress());
            return answer.getMessage();
        }
        if(destination != null&& question.contains("best")){
            List<Hotel> hotels = hotelservice.findHotelByLocation(destination);
            Hotel hotel = hotels.get(1);
            QuestionResponse answer = new QuestionResponse();
            answer.concatenateMessage(hotel.getHotelName());
            answer.concatenateMessage("Price : " + hotel.getHotelPrice());
            return answer.getMessage();
        }
        if(destination != null && question.contains("all hotels")){
            List<Hotel> hotels = hotelservice.findHotelByLocation(destination);
            QuestionResponse answer = new QuestionResponse();
            answer.setMessage("");
            for(Hotel hotel: hotels){
                answer.concatenateMessage(hotel.getHotelName() + "\n");
                answer.concatenateMessage("Price : " + hotel.getHotelPrice()+ "\n" );
            }
            return answer.getMessage();
        }
//        if(destination != null && !question.contains("hotels")){
//            return "retrieving all destinations";
//        }

        // give me all hotels in destination
        //give me all hotels // please enter destination
        //give me cheapest hotel in destination
        //give me best rated hotel in destination

        return "ok";
    }
}

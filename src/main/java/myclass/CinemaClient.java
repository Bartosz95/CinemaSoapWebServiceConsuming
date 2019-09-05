package myclass;

import movie.wsdl.GetMovieRequest;
import movie.wsdl.GetMovieResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


public class CinemaClient extends WebServiceGatewaySupport {

    public GetMovieResponse getMovieResponse(String movie){
        GetMovieRequest request = new GetMovieRequest();
        request.setTitle(movie);

        GetMovieResponse response = (GetMovieResponse)getWebServiceTemplate().marshalSendAndReceive(
                "http://localhost:8080/ws/cinema.wsdl",
                request,
                new SoapActionCallback("http://CinemaSevice.com/SoapWebServicesProducing/GetMovieRequest"));

        return response;
    }

}

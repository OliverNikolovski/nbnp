package mk.ukim.finki.nbnp.studentmanagementsystem.service;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.RequestType;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.request.RequestCreate;
import mk.ukim.finki.nbnp.studentmanagementsystem.repository.entity.RequestTypeRepository;
import mk.ukim.finki.nbnp.studentmanagementsystem.repository.jdbc.RequestJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    private final RequestTypeRepository requestTypeRepository;
    private final RequestJdbcRepository requestJdbcRepository;

    public RequestService(RequestTypeRepository requestTypeRepository, RequestJdbcRepository requestJdbcRepository) {
        this.requestTypeRepository = requestTypeRepository;
        this.requestJdbcRepository = requestJdbcRepository;
    }

    public List<RequestType> getAllRequestTypes() {
        return requestTypeRepository.findAll();
    }

    public void createRequestForStudent(RequestCreate request) {
        requestJdbcRepository.createRequestForStudent(request.studentId, request.requestTypeId, request.description);
    }
}

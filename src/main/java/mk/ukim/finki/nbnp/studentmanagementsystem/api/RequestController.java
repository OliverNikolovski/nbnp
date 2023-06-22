package mk.ukim.finki.nbnp.studentmanagementsystem.api;

import mk.ukim.finki.nbnp.studentmanagementsystem.model.entity.RequestType;
import mk.ukim.finki.nbnp.studentmanagementsystem.model.request.RequestCreate;
import mk.ukim.finki.nbnp.studentmanagementsystem.service.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/all")
    public List<RequestType> getAllRequestTypes() {
        return requestService.getAllRequestTypes();
    }

    @PostMapping
    public void createRequestForStudent(@RequestBody RequestCreate request) {
        requestService.createRequestForStudent(request);
    }
}

package ge.edu.freeuni.api;

import ge.edu.freeuni.api.model.mail.Mail;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.services.authentication.AuthenticationService;
import ge.edu.freeuni.server.services.friendship.FriendshipService;
import ge.edu.freeuni.server.services.mail.MailService;
import ge.edu.freeuni.server.services.passedQuiz.PassedQuizService;
import ge.edu.freeuni.server.services.quiz.QuizService;
import ge.edu.freeuni.server.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@Controller
public class MailController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private FriendshipService friendshipService;
    @Autowired
    private PassedQuizService passedQuizService;
    @Autowired
    private MailService mailService;

    @RequestMapping("/createMessage")
    public String createMessage() {
        return "createMessage";
    }

    @RequestMapping("/sendMessage")
    public String sendMessage(@RequestParam String receiverUsername,
                              @RequestParam String mailContext) {

        User toSend = userService.getUserByUsername(receiverUsername);
        Mail mail = new Mail();
        mail.setSender(authenticationService.getActiveUser());
        mail.setReceiver(toSend);
        mail.setContext(mailContext);
        mail.setSent_date(new Date());
        mailService.sendMail(mail);
        return "createMessage";
    }

    @RequestMapping("viewMessage/{id}")
    public String viewMessage(@PathVariable long id, Map<String, Object> model) {
        Mail mail = mailService.getMailById(id);
        model.put("mail", mail);
        return "viewMessage";
    }

}

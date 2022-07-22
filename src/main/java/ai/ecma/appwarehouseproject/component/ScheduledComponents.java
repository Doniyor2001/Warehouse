//package ai.ecma.appwarehouseproject.component;
//
//import ai.ecma.springexamapplication.entity.Notification;
//import ai.ecma.springexamapplication.payload.SendMessageDTO;
//import ai.ecma.springexamapplication.service.MailService;
//import ai.ecma.springexamapplication.service.NotificationService;
//import ai.ecma.springexamapplication.utils.AppConstant;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.sql.Timestamp;
//import java.util.Objects;
//
//;
//
//@Component
//@RequiredArgsConstructor
//public class ScheduledComponents {
//
//    private final NotificationService notificationService;
//    private final MailService mailService;
//
//    @Value("${spring.mail.username}")
//    private String myEmail;
//
//    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
//    public void sendNotification() {
//       Notification notification = notificationService.getBySendingNotificationDay(new Timestamp(System.currentTimeMillis()));
//       if (Objects.isNull(notification)) return;
//        SendMessageDTO sendMessageDTO = new SendMessageDTO(
//                "pdpacademy57@gmail.com",
//                myEmail,
//                AppConstant.NOTIFICATION,
//                "Please check expiry date of income product where unique_id " + notification.getIncomeProduct().getProduct().getProductUqId());
//        mailService.sendMessage(sendMessageDTO);
//    }
//
//}

package ai.ecma.appwarehouseproject.service.interfaces;


import ai.ecma.appwarehouseproject.entity.Product;
import ai.ecma.appwarehouseproject.payload.SendMessageDTO;

public interface MailService {

    boolean sendMessage(SendMessageDTO sendMessageDTO);

    boolean sendMessageToAdminEmail(Product product);
}

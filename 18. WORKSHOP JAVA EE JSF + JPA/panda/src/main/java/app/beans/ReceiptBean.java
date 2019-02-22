package app.beans;

import app.constatns.Constants;
import app.model.service.ReceiptServiceModel;
import app.model.view.ReceiptViewModel;
import app.services.api.ReceiptService;
import org.modelmapper.ModelMapper;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class ReceiptBean {

    private final ReceiptService receiptService;
    private final ModelMapper modelMapper;

    @Inject
    public ReceiptBean(ReceiptService receiptService, ModelMapper modelMapper) {
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
    }

    public List<ReceiptViewModel> allReceipts() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);

        return receiptService.getAllByRecipient((String) httpSession.getAttribute(Constants.LOGGED_USER))
                .stream()
                .map(receiptServiceModel -> modelMapper.map(receiptServiceModel, ReceiptViewModel.class))
                .collect(Collectors.toList());
    }

    public ReceiptViewModel receiptInfo() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String uuid = facesContext.getExternalContext().getRequestParameterMap().get("id");
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
        String username = (String) httpSession.getAttribute(Constants.LOGGED_USER);

        ReceiptServiceModel receiptServiceModel = receiptService.getByUsernameAndUUID(username, uuid);
        return modelMapper.map(receiptServiceModel, ReceiptViewModel.class);

    }
}
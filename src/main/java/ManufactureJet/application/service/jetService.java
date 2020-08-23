package ManufactureJet.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ManufactureJet.application.DTO.jetDto;
import ManufactureJet.application.DTO.jetOrderDTO;
import ManufactureJet.application.Entity.jetEntity;
import ManufactureJet.application.Entity.jetOrderEntity;
import ManufactureJet.application.repository.JetOrderRepository;
import ManufactureJet.application.repository.jetRepository;
@Service
public class jetService {
	@Autowired
	jetRepository repo;
	
	@Autowired
	JetOrderRepository orderrepo;
	
	public List<jetDto> getModel(){
		List<jetEntity> jetModel= repo.findAll();
		List<jetDto> modelList= new ArrayList<jetDto>();
		for(jetEntity j1:jetModel) {
			jetDto Model = jetDto.valueOf(j1);
			modelList.add(Model);
		}
		return modelList;
	}
	
	public jetDto getModelById(String manufactureModelNo) {
		jetDto model= null;
		Optional<jetEntity> entity=repo.findById(manufactureModelNo);
		if(entity.isPresent()) {
			if(manufactureModelNo.equals(entity.get().getManufactureModelNo())) {
				model=jetDto.value(entity);
				}
		}
		return model;
	}

	public void orderJet(jetOrderDTO order) throws Exception {
		System.out.println("in entity");
		Optional<jetEntity> entity=repo.findById(order.getQuotationmodelno());
		System.out.println(entity);
		if(entity.isPresent()) {
			if(order.getQuotationmodelno().equals(entity.get().getManufactureModelNo())) {
				jetDto model=jetDto.value(entity);
				System.out.println(model);
				jetOrderEntity jetOrder= order.createEntity(model);
				System.out.println(jetOrder);
				orderrepo.save(jetOrder);
			}
		}else {
			throw new Exception("Invalid Quotation Model Number");
		}
		
	}

	public void deleteOrder(Integer orderid) throws Exception {
		Optional<jetOrderEntity> entity= orderrepo.findById(orderid);
		if(entity.isPresent()) {
			orderrepo.deleteById(orderid);
		}else {
			throw new Exception("Order ID Unavailable");
		}
	}

	public jetDto getModelByName(String modelName) {
		jetDto model= null;
		jetEntity entity=repo.findBymodelName(modelName);
		model=jetDto.valueOf(entity);
		return model;
	}

	public List<jetOrderDTO> getvieworder() {
		List<jetOrderEntity> entity=orderrepo.findAll();
		List<jetOrderDTO> orderdto=new ArrayList<jetOrderDTO>();
		for(jetOrderEntity en:entity) {
			jetOrderDTO dto=jetOrderDTO.value(en);
			orderdto.add(dto);
		}
		
		return orderdto;
	}

	public jetOrderDTO getViewOrderById(Integer orderid) throws Exception {
		Optional<jetOrderEntity> entity= orderrepo.findById(orderid);
		if(entity.isPresent()) {
			jetOrderDTO jetorderDTO = jetOrderDTO.valueOf(entity);
			return jetorderDTO ;
		}
		else {
			throw new Exception("Order ID Unavailable");
		}
		
	}
}

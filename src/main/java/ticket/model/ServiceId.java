package ticket.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "Service_Id")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceId {
	@Id
	private String id;
	private int seq;

}

package ticket.model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Document(collection = "Ticket")
@Data
@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Ticket {
	@Transient
    public static final String SEQUENCE_NAME = "user_sequence";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY
	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			)
	private Integer id_ticket;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime date;
	private Long cin;
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	private Boolean res=false;
	private String code;

}

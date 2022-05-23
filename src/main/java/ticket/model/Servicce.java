package ticket.model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Document(collection = "Servicce")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Servicce {
	@Transient
    public static final String SEQUENCE_NAME = "Ticket_sequence";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_ticket;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime date;
}

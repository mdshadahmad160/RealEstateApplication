package org.psa.RealEstate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RealEstateApplication implements CommandLineRunner {
  @Autowired
  private PasswordEncoder passwordEncoder;
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}



	public static void main(String[] args){
		SpringApplication.run(RealEstateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("SHAD@34"));

	}
}

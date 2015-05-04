package pl.fastresponse;

import javax.annotation.Resource;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import pl.fastresponse.person.Person;
import pl.fastresponse.person.PersonRepository;

@SpringBootApplication
@Configuration
@EnableElasticsearchRepositories(basePackages = "pl.fastresponse.person")
public class SimpleCrmApplication {

	@Resource
	private Environment environment;

	@Bean
	public Client client() {
		TransportClient client = new TransportClient();
		TransportAddress address = new InetSocketTransportAddress(
				environment.getProperty("elasticsearch.host"),
				Integer.parseInt(environment.getProperty("elasticsearch.port")));
		client.addTransportAddress(address);
		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		return new ElasticsearchTemplate(client());
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(
				SimpleCrmApplication.class, args);
		PersonRepository bean = (PersonRepository) run
				.getBean("personRepository");

		Person p1 = new Person();
		p1.setName("Paweł Pietrzak");
		bean.save(p1);

		Iterable<Person> all = bean.findAll();
		for (Person person : all) {
			System.out.println(person.getName());
		}
	}
}

package com.buana.technical_test_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.SpringDataMongoDB;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.MongoConfigurationSupport;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.lang.Nullable;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientSettings.Builder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MongoConfig extends MongoConfigurationSupport{

	
	@Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
	
	/**
	 * Creates a {@link MappingMongoConverter} using the configured {@link #mongoDbFactory()} and
	 * {@link #mongoMappingContext(MongoCustomConversions)}. Will get {@link #customConversions()} applied.
	 *
	 * @see #customConversions()
	 * @see #mongoMappingContext(MongoCustomConversions)
	 * @see #mongoDbFactory()
	 */
	@Bean
	public MappingMongoConverter mongoConverter(MongoDatabaseFactory databaseFactory,
			MongoCustomConversions customConversions, MongoMappingContext mappingContext) throws Exception {
		DbRefResolver dbRefResolver = new DefaultDbRefResolver(databaseFactory);
		MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mappingContext);
		mongoConverter.setCustomConversions(customConversions);
		mongoConverter.setCodecRegistryProvider(databaseFactory);
		mongoConverter.setMapKeyDotReplacement("#!#");
		return mongoConverter;
	}

	@Override
	protected String getDatabaseName() {
		return "buana";
	}
	
	/**
	 * Return the {@link MongoClient} instance to connect to. Annotate with {@link Bean} in case you want to expose a
	 * {@link MongoClient} instance to the {@link org.springframework.context.ApplicationContext}. <br />
	 * Override {@link #mongoClientSettings()} to configure connection details.
	 *
	 * @return never {@literal null}.
	 * @see #mongoClientSettings()
	 * @see #configureClientSettings(Builder)
	 */
	public MongoClient mongoClient() {
		return createMongoClient(mongoClientSettings());
	}

	/**
	 * Creates a {@link MongoTemplate}.
	 *
	 * @see #mongoDbFactory()
	 * @see #mappingMongoConverter(MongoDatabaseFactory, MongoCustomConversions, MongoMappingContext)
	 */
	@Bean
	public MongoTemplate mongoTemplate(MongoDatabaseFactory databaseFactory, MappingMongoConverter converter) {
		return new MongoTemplate(databaseFactory, converter);
	}

	/**
	 * Creates a {@link org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory} to be used by the
	 * {@link MongoTemplate}. Will use the {@link MongoClient} instance configured in {@link #mongoClient()}.
	 *
	 * @see #mongoClient()
	 * @see #mongoTemplate(MongoDatabaseFactory, MappingMongoConverter)
	 */
	@Bean
	public MongoDatabaseFactory mongoDbFactory() {
		log.info("DB NAME: " + getDatabaseName());
		return new SimpleMongoClientDatabaseFactory(mongoClient(), getDatabaseName());
	}

	/**
	 * Return the base package to scan for mapped {@link Document}s. Will return the package name of the configuration
	 * class' (the concrete class, not this one here) by default. So if you have a {@code com.acme.AppConfig} extending
	 * {@link AbstractMongoClientConfiguration} the base package will be considered {@code com.acme} unless the method is
	 * overridden to implement alternate behavior.
	 *
	 * @return the base package to scan for mapped {@link Document} classes or {@literal null} to not enable scanning for
	 *         entities.
	 * @deprecated use {@link #getMappingBasePackages()} instead.
	 */
	@Deprecated
	@Nullable
	protected String getMappingBasePackage() {

		Package mappingBasePackage = getClass().getPackage();
		return mappingBasePackage == null ? null : mappingBasePackage.getName();
	}

	/**
	 * Create the Reactive Streams {@link com.mongodb.reactivestreams.client.MongoClient} instance with given
	 * {@link MongoClientSettings}.
	 *
	 * @return never {@literal null}.
	 * @since 3.0
	 */
	protected MongoClient createMongoClient(MongoClientSettings settings) {
		return MongoClients.create(settings, SpringDataMongoDB.driverInformation());
	}
}


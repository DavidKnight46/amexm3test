package org.futurlab.amexexcerise.configuration

import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileReader

@Configuration
class AppConfiguration(@Value("\${mybatis.url}") url : String) {

    var fileReader = FileReader(url);

    @Bean
    fun createSQLSession(): SqlSession {
        return SqlSessionFactoryBuilder()
            .build(fileReader)
            .openSession()
    }
}
/*
 * Copyright 2010-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.fpu.eiiv.qrcode.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fpu.eiiv.qrcode.domain.Message;
import br.com.fpu.eiiv.qrcode.domain.Participante;
import br.com.fpu.eiiv.qrcode.repository.ParticipanteRepository;

@Controller
@RequestMapping("/*")
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired ParticipanteRepository participanteRepository;

	@RequestMapping(value = "/mensagem", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Message getMessage() {
		logger.debug("mensagem solicitada");
		return new Message(100, "Congratulations!", "You have accessed a Basic Auth protected resource.");
	}
	
	@RequestMapping(value = "/participantes", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Iterable<Participante> getParticipantes() {
		return participanteRepository.findAll();
	}
	
	@RequestMapping(value = "/participante", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Participante insertParticipante(@RequestBody Participante participante) {
		logger.debug(String.format("Salvando %s", participante));
		return participanteRepository.save(participante);
	}
	
	@RequestMapping(value = "/participante/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Participante getParticipante(@PathVariable("id") long id) {
		logger.debug(String.format("Buscando participante com o id %s", id));
		Participante participante = participanteRepository.findOne(id);
		return participante;
	}
	
}

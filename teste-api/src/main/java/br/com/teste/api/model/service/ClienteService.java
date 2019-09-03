package br.com.teste.api.model.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.teste.api.model.beans.Cliente;
import br.com.teste.api.model.beans.Contato;
import br.com.teste.api.model.beans.Endereco;
import br.com.teste.api.model.beans.EstadoCivil;
import br.com.teste.api.model.beans.Pessoa;
import br.com.teste.api.model.beans.PessoaFisica;
import br.com.teste.api.model.beans.PessoaJuridica;
import br.com.teste.api.model.beans.SituacaoCliente;
import br.com.teste.api.model.beans.Telefone;
import br.com.teste.api.model.beans.TipoTelefone;
import br.com.teste.api.model.beans.UF;
import br.com.teste.api.model.repository.ClienteRepository;
import br.com.teste.api.model.repository.ContatoRepository;
import br.com.teste.api.model.repository.ObjetoValorRepository;
import br.com.teste.api.model.repository.PessoaRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ObjetoValorRepository objetoValorRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findOne(Integer id) {
		return clienteRepository.findOne(id);
	}

	public List<Cliente> findByNome(String nome) {
		return clienteRepository.findByNome(nome);
	}

	public Cliente save(Cliente cliente) {
		Pessoa pessoa = cliente.getPessoa();
		if (pessoa instanceof PessoaFisica) {
			pessoaRepository.save(pessoa);
		} else {
			PessoaJuridica pj = (PessoaJuridica)pessoa;
			pessoaRepository.save(pj);
			List<Contato> contatos = pj.getContatos();
			contatos.forEach(contato-> { 
				contato.setPessoaJuridica(pj);
				contatoRepository.save(contato);
			});
		}
		
		Cliente clienteNovo =  clienteRepository.save(cliente);
		return clienteNovo;
	}
	
	public void delete(Integer id) {
		this.clienteRepository.delete(id);
	}

	@Transactional
	public void executaTeste() {
		final long SITUACAO_ATIVO = 1l;
		Cliente cliente = new Cliente () ;
		
		cliente.setDataCadastro(LocalDate.now());
		cliente.setObservacao("Teste de criacao");
		cliente.setSituacao(this.findSituacao(SITUACAO_ATIVO));
		cliente.setPessoa(this.criaPessoa());
		this.clienteRepository.save(cliente);
		
	}
	
	private SituacaoCliente findSituacao (Long id) {
		SituacaoCliente situacao = (SituacaoCliente) this.objetoValorRepository.findOne(id);
		if(situacao==null) {
			throw new RuntimeException("Situacao de cliente id [" + id + " ]");
		}
		return situacao;
	}
	
	private Pessoa criaPessoa() {
		PessoaFisica pessoa = new PessoaFisica();
		pessoa.setEmail("teste@teste.com.br");
		pessoa.setTelefone1(criaTelefone("RESIDENCIAL", "111-1111"));
		pessoa.setTelefone2(criaTelefone("CELULAR", "222-2222"));
		pessoa.setEndereco(criaEndereco("Logradouro 1", "Pedreira" ,"Belem"));
		pessoa.setCpf("11111111");
		pessoa.setNome("Pessoa Fisica 1");
		pessoa.setDataNascimento(LocalDate.of(1980, 1, 8));
		pessoa.setEstadoCivil(criaEstadoCivil("CASADO"));
		this.pessoaRepository.save(pessoa);
		return pessoa;
	}
	
	private Telefone criaTelefone(String codigo, String numero) {
		TipoTelefone tipo = (TipoTelefone) this.objetoValorRepository.findByCodigo(codigo);
		if (tipo==null) {
			throw new RuntimeException("Não encontrado tipo telefone");
		}
		return new Telefone("91", numero, tipo );
	}
	
	private Endereco criaEndereco(String logradouro, String bairro, String cidade) {
		Endereco endereco = new Endereco();
		endereco.setLogradouro(logradouro);
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		endereco.setUf(UF.PA);
		return endereco;
	}
	
	private EstadoCivil criaEstadoCivil(String codigo) {
		EstadoCivil estado = (EstadoCivil) this.objetoValorRepository.findByCodigo(codigo);
		if (estado==null) {
			throw new RuntimeException("Não encontrado estado civil codigo ["+ codigo +"]");
		}
		return estado;
	}

}

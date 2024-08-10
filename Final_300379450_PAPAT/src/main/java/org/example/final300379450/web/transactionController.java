//Papat Jiyaworanant
//300379450

package org.example.final300379450.web;
// import section -------------------------------------
import jakarta.transaction.Transaction;
import org.example.final300379450.entities.transaction;
import org.example.final300379450.repository.transactionRepository;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.BindingResult;

@AllArgsConstructor
@SessionAttributes({"a","e"})
@Controller


public class transactionController {
    @Autowired
    private transactionRepository transactionRepository;
    static int num  = 0;

    @GetMapping(path  = "/index")
    //list transaction by Id
    public String transactions(Model model, @RequestParam(name="keyword",defaultValue="") String keyword) {
        List<transaction> transactions;
        if (keyword.isEmpty())  {
            transactions = transactionRepository.findAll();
        }
        else {
            long key = Long.parseLong(keyword);
            transactions = transactionRepository.findTransactionById(key);
        }
        model.addAttribute("listTransactions", transactions);
        return "transactions";
    }
    //end of list transaction


    //add transaction
    @GetMapping("/addTransactions")
    public String addTransactions(Model model) {
        model.addAttribute("transaction", new transaction());
        return "addTransactions";
    }


    //save data
    @PostMapping(path = "/save")
    public String save(Model model, Transaction transaction, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "addTransactions";
        }
        else {
            transactionRepository.save(transaction);
            if (num == 2) {
                mm.put("e", 2);
                mm.put("a", 0);
            } else {
                mm.put("a", 1);
                mm.put("e", 0);
            }
            return "redirect:index";
        }
    }
    //end of save data


    //delete data
    @GetMapping("/delete")
    public String delete(Long id)  {
        transactionRepository.deleteById(id);
        return "redirect:/index";
    }
    //end of delete

    //edit transaction
    @GetMapping("/editTransactions")
    public String editTransactions(Model model, Long id, HttpSession session) {
        num = 2;
        session.setAttribute("info", 0);
        //Transaction transaction = transactionRepository.findAllById(id).orElse(null);
        List<transaction> transaction = transactionRepository.findAll();
        if (transaction == null) throw new RuntimeException("Customer does not exist");
        model.addAttribute("transaction", transaction);
        return "editTransactions";
    }
    //end of edit transaction

}

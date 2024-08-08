import Foundation
import UIKit
import SwiftUI
import Combine // Add this import statement
import ComposeApp



struct MainScreenCompose: View {
    @State var onButtonClick: () -> Void
    
    var body: some View {
        MainViewControllerCompose(onButtonClick: onButtonClick)
    }
}

struct MainViewControllerCompose: UIViewControllerRepresentable {
    let onButtonClick: () -> Void
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(onButtonClick: onButtonClick)
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) { }
}



// SwiftUI for UIKit Representable
struct UIKitBlockView: UIViewControllerRepresentable {
    let onButtonClick: () -> Void
    
    func makeUIViewController(context: Context) -> UIKitBlockViewController {
        UIKitBlockViewController {
            onButtonClick()
        }
    }

    func updateUIViewController(_ uiViewController: UIKitBlockViewController, context: Context) {  }
}

// UIKit ViewController
class UIKitBlockViewController: UIViewController {
    var onButtonClick: () -> Void
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    init(onButtonClick: @escaping () -> Void) {
         self.onButtonClick = onButtonClick
         super.init(nibName: nil, bundle: nil)
     }
        
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = .red
        
        let titleLabel = UILabel()
        titleLabel.text = "UIKit Block Title"
        titleLabel.font = UIFont.preferredFont(forTextStyle: .headline)
        view.addSubview(titleLabel)
        
        let actionButton = UIButton(type: .system)
        actionButton.setTitle("Tap Me!", for: .normal)
        actionButton.tintColor = .gray
        actionButton.addTarget(self, action: #selector(buttonTapped), for: .touchUpInside)
        view.addSubview(actionButton)
        
        // Layout (Use Auto Layout, SnapKit, or programmatic constraints here)
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        actionButton.translatesAutoresizingMaskIntoConstraints = false


        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 20),
            titleLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor),

            actionButton.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant:
 20),
            actionButton.centerXAnchor.constraint(equalTo: view.centerXAnchor)
        ])
    }

    @objc func buttonTapped() {
        onButtonClick()
        //print("Button in UIKit Block Tapped!")
    }
}

//////



// SwiftUI for ComposeUI Representable
struct ComposeView: UIViewControllerRepresentable {
    var onButtonClick: () -> Void
    func makeUIViewController(context: Context) -> UIViewController {
        ComponentsIOSKt.createComposeForSheetViewController{
            onButtonClick()
        }
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) { }
}



struct CustomSheetSwiftUI: View {
  var title: String
  var message: String
  var dismissButtonTitle: String
  @Binding var isButtonClicked: Bool
  var onButtonClickBlock: () -> Void
  @State var keyboardHeight: CGFloat = 0

  var body: some View {
      VStack {
        Text(title).font(.headline)
        Text(message)
          .lineLimit(nil)
          .fixedSize(horizontal: false, vertical: true)

        Text("Text text text text tte xtt ex fasd  dftt")
          .lineLimit(nil)
          .fixedSize(horizontal: false, vertical: true)
          .frame(width: 50) // Set a fixed width

        Button(dismissButtonTitle) {
//            isButtonClicked = false
            print("CustomSheetSwiftUI: Button clicked")
            onButtonClickBlock()
        }
      }
      .frame(maxWidth: .infinity, maxHeight: .infinity)
      .background(Color.green) //Background of the block VStack
    }
}


struct CustomSheetCompose: View {
  var title: String
  var message: String
  var dismissButtonTitle: String
  @Binding var isButtonClicked: Bool
  var onButtonClickBlock: () -> Void
  @State var keyboardHeight: CGFloat = 0

  var body: some View {
      VStack {
          ComposeView(onButtonClick: onButtonClickBlock)
//              .frame(maxWidth: .infinity, maxHeight: .infinity)  // SwiftUI layout
//              .cornerRadius(10)
//                        .frame(maxWidth: .infinity) // Take up available width
//              .padding()
//              .background(Color.red)
//              .cornerRadius(10)
      }
//      .frame(maxWidth: .infinity, maxHeight: .infinity)
      .background(Color.green) //Background of the block VStack
    }
}


